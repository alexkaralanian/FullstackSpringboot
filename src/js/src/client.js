import axios from 'axios';

const formatError = err => {
  return { error: err.message };
};

export const getAllStudents = async () => {
  try {
    const { data } = await axios.get('api/students');
    return data;
  } catch (err) {
    return formatError(err);
  }
};

export const addNewStudent = async student => {
  try {
    const { data } = await axios.post('api/students', student);
    return data;
  } catch (err) {
    return formatError(err);
  }
};
