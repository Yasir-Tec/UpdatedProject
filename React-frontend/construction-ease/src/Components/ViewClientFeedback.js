import React, { useEffect, useState } from 'react';
import { Button, Table } from 'react-bootstrap';
import AdminService from '../Services/AdminService';
import AdminHeaderComponent from './AdminHeader';
const ViewClientFeedbackComponent = () => {
  const [feedback, setFeedback] = useState([]);

  useEffect(() => {
    ViewFeedBack();
  }, []);

  const ViewFeedBack = () => {
    AdminService.viewlientFeedback()
      .then((response) => {
        console.log(response.data);
        // Ensure that the response.data is an array before updating the state
        if (Array.isArray(response.data)) {
          setFeedback(response.data);
        } else {
          console.error("Invalid data format. Expected an array.");
          setFeedback([]); // Set feedback to an empty array to prevent the error
        }
      })
      .catch((error) => {
        console.log(error);
        alert("Don't have any feedBack,Feedback could not be Loaded !!")
      });
  };

  const deleteFeedback = (feedbackId) => {
    console.log("Deleting feedback with ID:", feedbackId);
    AdminService.deleteFeedbackClient(feedbackId)
      .then(() => {
        ViewFeedBack(); // Refresh feedback after deletion
      })
      .catch((error) => {
        console.log(error);
      });
  };

  return (
    <>
    <AdminHeaderComponent></AdminHeaderComponent>
    <div className="container" style={{ marginTop: "100px" }}>
      <h2 className="text-center">Client Feedback List</h2>
     
      <Table bordered striped>
        <thead>
          <tr>
            <th>Id</th>
            <th>Time</th>
            <th>Feedback</th>
            <th>Action</th>
          </tr>
        </thead>
        <tbody>
          {feedback.map((feedbackItem) => (
            <tr key={feedbackItem.id}>
              <td>{feedbackItem.id}</td>
              <td>{feedbackItem.time}</td>
              <td>{feedbackItem.feedback}</td>
              <td>
                <Button
                  variant="danger"
                  onClick={() => deleteFeedback(feedbackItem.id)}
                  style={{ marginLeft: "10px" }}
                >
                  Delete
                </Button>
              </td>
            </tr>
          ))}
        </tbody>
      </Table>
    </div>
    </>
  );
};

export default ViewClientFeedbackComponent;