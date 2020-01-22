import React from 'react';
import './App.css';
import { getAllStudents } from './client';
import { Table, Avatar, Spin, Modal, Empty } from 'antd';
import Footer from './Footer';
import styled from 'styled-components';
import AddStudentForm from './AddStudentForm';

const Container = styled.div`
  width: 900px;
  margin: 20px auto;
  text-align: center;
`;

function App() {
  const [students, setStudents] = React.useState([]);
  const [isFetching, setIsFetching] = React.useState(false);
  const [error, setError] = React.useState('');
  const [
    isAddStudentModalVisible,
    setIsAddStudentModalVisible
  ] = React.useState(false);

  React.useEffect(() => {
    setIsFetching(true);
    getAllStudents().then(students => {
      if (students.error) {
        setError(students.error);
        setIsFetching(false);
      }
      setStudents(students);
      setIsFetching(false);
    });
  }, []);

  const openAddStudentModal = () => setIsAddStudentModalVisible(true);
  const closeAddStudentModal = () => setIsAddStudentModalVisible(false);

  const columns = [
    {
      title: '',
      key: 'avatar',
      render: (text, student) => (
        <Avatar size="large">
          {`${student.firstName.charAt(0).toUpperCase()}
            ${student.lastName.charAt(0).toUpperCase()}`}
        </Avatar>
      )
    },
    {
      title: 'Id',
      dataIndex: 'studentId',
      key: 'studentId'
    },
    {
      title: 'First Name',
      dataIndex: 'firstName',
      key: 'firstName'
    },
    {
      title: 'Last Name',
      dataIndex: 'lastName',
      key: 'lastName'
    },
    {
      title: 'Gender',
      dataIndex: 'gender',
      key: 'gender'
    },
    {
      title: 'Email',
      dataIndex: 'email',
      key: 'email'
    }
  ];

  const renderView = component => {
    if (isFetching) {
      return <Spin size="large" />;
    }
    if (error) {
      return <div>{error}</div>;
    }
    if (students && !students.length) {
      return <Empty description={<h1>No Students Found</h1>} />;
    }
    return component;
  };

  return (
    <Container>
      <div>
        <h1>FullStack Springboot & React</h1>

        {renderView(
          <div>
            <Table
              dataSource={students}
              columns={columns}
              rowKey="studentId"
              style={{ marginBottom: '100px' }}
            />
            <Modal
              title="Add New Student"
              visible={isAddStudentModalVisible}
              onOk={closeAddStudentModal}
              onCancel={closeAddStudentModal}
              width={1000}
            >
              <AddStudentForm
                onSuccess={() => {
                  closeAddStudentModal();
                  setIsFetching(true);
                  getAllStudents().then(students => {
                    setStudents(students);
                    setIsFetching(false);
                  });
                }}
                handleReset
              />
            </Modal>
            <Footer
              numberOfStudents={students.length}
              openAddStudentModal={openAddStudentModal}
            />
          </div>
        )}
      </div>
    </Container>
  );
}

export default App;
