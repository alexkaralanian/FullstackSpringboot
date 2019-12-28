import axios from 'axios';

export const getAllStudents = async () => {
  try {
    const { data } = await axios.get('api/students');
    return data;
  } catch (err) {
    console.error(err);
  }
};

export const addNewStudent = async student => {
  console.log('ADD NEW STUDENT', student);
  try {
    const { data } = await axios.post('api/students', student);
    return data;
  } catch (err) {
    console.error(err);
  }
};
