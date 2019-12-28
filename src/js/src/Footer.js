import React from 'react';
import { Button, Avatar } from 'antd';
import styled from 'styled-components';

const Footer = styled.div`
  position: fixed;
  bottom: 0;
  left: 0;
  right: 0;
  background: rgba(240, 240, 240, 0.9);
  height: 5em;
  padding: 1em;
`;

const style = {
  marginRight: '10px',
  backgroundColor: '#f56a00'
};

const FooterComponent = ({ numberOfStudents, openAddStudentModal }) => (
  <Footer>
    {numberOfStudents && (
      <Avatar size="large" style={style}>
        {numberOfStudents}
      </Avatar>
    )}
    <Button type="primary" onClick={openAddStudentModal}>
      Add new student +
    </Button>
  </Footer>
);

export default FooterComponent;
