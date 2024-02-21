import React, { useEffect, useState } from 'react';
import ContractorService from '../Services/ContractorService';
import { Link } from 'react-router-dom';

import AdminHeaderComponent from './AdminHeader';
const ListContractorsComponent = () => {
  const [contractor, setcontractor] = useState([]);

  useEffect(() => {
    getAllContractors();
  }, []);

  const getAllContractors = () => {
    ContractorService.getAllContractors()
      .then((response) => {
        console.log(response.data);
        setcontractor(response.data);
      })
      .catch((error) => {
        console.log(error);
      });
  };




  const deleteContractor = (contractorId) => {
    console.log(".........code is .....");
    console.log(contractorId);
    ContractorService.deleteContractor(contractorId)
      .then((response) => {
        getAllContractors();
      })
      .catch((error) => {
        console.log(error);
      });
  };

  return (
    <>
   <AdminHeaderComponent></AdminHeaderComponent>
    <div className="container" style={{ marginTop: "100px" }}>
      <h2 className="text-center">Contractor List</h2>
     
      <table className="table table-bordered table-striped">
        <thead>
          <tr>
            <th>Id</th>
            <th>Email</th>
            <th>Mobile No</th>
            <th>Username</th>
            <th>Password</th>
            <th>Actions</th>
          </tr>
        </thead>
        <tbody>
          {contractor.map((contractor) => (
            <tr key={contractor.id}>
              <td>{contractor.id}</td>
              <td>{contractor.email}</td>
              <td>{contractor.mobile}</td>
              <td>{contractor.username}</td>
              <td>{contractor.password}</td>
              <td>
            
                <button
                  className="btn btn-danger"
                  onClick={() => deleteContractor(contractor.id)}
                  style={{ marginLeft: "10px" }}
                >
                  Delete
                </button>
              </td>
            </tr>
          ))}
        </tbody>
      </table>
    </div>
    </>
  );
};

export default ListContractorsComponent;
