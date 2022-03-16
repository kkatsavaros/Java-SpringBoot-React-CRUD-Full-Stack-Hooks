import React, { useState, useEffect } from 'react'
import EmployeeService from '../services/EmployeeService'
import { Link } from 'react-router-dom';

const ListEmployeeComponent = () => {

    const [employees, setEmployees] = useState([]) // The initial value is an empty array.   

    useEffect(() => { // The useEffect Hook allow us to perform side effects (an action) in the function components.
        // Effect Hooks are equivalent to componentDidMount(), componentDidUpdate() and componentWillUnmount() lifecycle methods. 

        EmployeeService.getAllEmployees().then((response) => {
            setEmployees(response.data)
            console.log(response.data);
        }).catch(error => {
            console.log(error);
        })

    }, [])


    const getAllEmployees = () => {
        EmployeeService.getAllEmployees().then((response) => {
            setEmployees(response.data)
            console.log(response.data);
        }).catch(error => {
            console.log(error);
        })
    }


    const deleteEmployee = (employeeId) => {
        console.log("We want to delete id:" + employeeId);


        EmployeeService.deleteEmployee(employeeId).then((response) => {
            getAllEmployees()
        }).catch(error => {
            console.log(error);
        })

    }



    // Δεν υπάρχει render method.
    return (
        <div className="container">
            <h2 className="text-center">List Employees</h2>
            <Link to="/add-employee" className="btn btn-primary mb-2">Add Employee</Link>
            <table className="table table-bordered table-striped">
                <thead>
                    <tr><th>Employee Id</th><th>Employee First Name</th><th>Employee Last Name</th><th>Employee Email Id</th><th>Actions</th></tr>
                </thead>
                <tbody>

                    {
                        employees.map(
                            employee =>
                                <tr key={employee.id}>
                                    <td>{employee.id}</td>
                                    <td>{employee.firstname}</td>
                                    <td>{employee.lastname}</td>
                                    <td>{employee.emailId}</td>

                                    <td><Link to={`/edit-employee/${employee.id}`} className="btn btn-info">Update</Link>

                                        <button className="btn btn-danger"
                                            onClick={() => deleteEmployee(employee.id)}
                                            style={{ marginLeft: "10px" }}>Delete</button>

                                    </td>
                                </tr>
                        )
                    }
                    
                </tbody>
            </table>
        </div>
    )
}

export default ListEmployeeComponent
