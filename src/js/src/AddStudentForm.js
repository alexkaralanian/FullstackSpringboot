import React from "react";
import { Formik } from "formik";
import { Input, Button } from "antd";
import { addNewStudent } from "./client";

const AddStudentForm = ({ onSuccess }) => {
  const inputStyles = {
    marginBottom: "5px"
  };

  return (
    <Formik
      initialValues={{ firstName: "", lastName: "", email: "", gender: "" }}
      validate={values => {
        const errors = {};
        if (!values.email) {
          errors.email = "Required";
        } else if (
          !/^[A-Z0-9._%+-]+@[A-Z0-9.-]+\.[A-Z]{2,}$/i.test(values.email)
        ) {
          errors.email = "Invalid email address";
        }
        if (!values.firstName) {
          errors.firstName = "Required";
        }
        if (!values.lastName) {
          errors.lastName = "Required";
        }
        if (!values.gender) {
          errors.gender = "Required";
        } else if (
          !["MALE", "male", "FEMALE", "female"].includes(values.gender)
        ) {
          errors.gender = "Gender must be MALE, male, FEMALE, or female";
        }
        return errors;
      }}
      onSubmit={(values, { setSubmitting }) => {
        addNewStudent(values).then(() => {
          onSuccess();
        });
      }}
    >
      {({
        values,
        errors,
        touched,
        handleChange,
        handleBlur,
        handleSubmit,
        isSubmitting,
        submitForm,
        isValid
        /* and other goodies */
      }) => (
        <form onSubmit={handleSubmit}>
          <Input
            style={inputStyles}
            name="firstName"
            onChange={handleChange}
            onBlur={handleBlur}
            value={values.firstName}
            placeholder="First Name"
          />
          {errors.firstName && touched.firstName && errors.firstName}
          <Input
            style={inputStyles}
            name="lastName"
            onChange={handleChange}
            onBlur={handleBlur}
            value={values.lastName}
            placeholder="Last Name"
          />
          {errors.lastName && touched.lastName && errors.lastName}
          <Input
            style={inputStyles}
            name="email"
            onChange={handleChange}
            onBlur={handleBlur}
            value={values.email}
            placeholder="Email"
          />
          {errors.email && touched.email && errors.email}
          <Input
            style={inputStyles}
            name="gender"
            onChange={handleChange}
            onBlur={handleBlur}
            value={values.gender}
            placeholder="Gender"
          />
          {errors.gender && touched.gender && errors.gender}
          <Button
            type="submit"
            onClick={submitForm}
            disabled={isSubmitting || (touched && !isValid)}
          >
            Submit
          </Button>
        </form>
      )}
    </Formik>
  );
};

export default AddStudentForm;
