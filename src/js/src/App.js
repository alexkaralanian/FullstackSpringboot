import React from 'react';
import './App.css';
import { getAllStudents } from './client';
import { Table, Avatar, Spin } from 'antd';
import styled from 'styled-components';

const Container = styled.div`
  width: 900px;
  margin: 20px auto;
  text-align: center;
`;

function App() {
  const [students, setStudents] = React.useState([]);
  const [isFetching, setIsFetching] = React.useState(false);

  React.useEffect(() => {
    setIsFetching(true);
    getAllStudents().then(students => {
      setStudents(students);
      setIsFetching(false);
    });
  }, []);

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

  return (
    <Container>
      <div>
        <h1>{'FullStack Springboot & React'}</h1>
        {isFetching ? (
          <Spin size="large" />
        ) : (
          <Table dataSource={students} columns={columns} />
        )}
      </div>
    </Container>
  );
}

export default App;
