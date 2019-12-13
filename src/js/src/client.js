import axios from 'axios';

export const getAllStudents = async () => {
  try {
    const { data } = await axios.get('api/students');
    return data;
  } catch (err) {
    console.error(err);
  }
};
