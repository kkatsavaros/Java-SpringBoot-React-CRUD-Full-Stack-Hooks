import React, { useState, useEffect } from 'react'              // 1. Update
import EmployeeService from '../services/EmployeeService'
import { Link, useHistory, useParams } from 'react-router-dom'; // 2. Update

const AddEmployeeComponent = () => {

    const [firstname, setFirstName] = useState('')
    const [lastname, setLastName] = useState('')
    const [emailId, setEmailId] = useState('')

    const history = useHistory();
    const { id } = useParams(); // 3. Update


    const saveOrUpdateEmployee = (e) => {
        e.preventDefault();

        const employee = { firstname, lastname, emailId } // Δημιουργία αντικειμένου.

        if (id) { // Update
            console.log('employee object: => ' + JSON.stringify(employee));

            EmployeeService.updateEmployee(id, employee)
                .then(response => {
                    console.log(response.data)

                    history.push('/employees'); // Αν όλα πάνε καλά πήγαινε στην αρχική λίστα.

                }).catch(error => {
                    console.log(error)
                })
        } else { // Add

            console.log('employee object: => ' + JSON.stringify(employee));

            EmployeeService.createEmployee(employee)
                .then(response => {
                    console.log(response.data)

                    history.push('/employees'); // Αν όλα πάνε καλά πήγαινε στην αρχική λίστα.

                }).catch(error => {
                    console.log(error)
                })
        }
    }



    // 4. Update
    useEffect(() => {
        EmployeeService.getEmployeeById(id)
            .then((response) => {
                setFirstName(response.data.firstname)
                setLastName(response.data.lastname)
                setEmailId(response.data.emailId)
            }).catch(error => {
                console.log(error)
            })

    }, [])

    // 5. Update
    const title = () => {
        if (id) {
            return <h2 className="text-center">Update Employee </h2>
        } else {
            return <h2 className="text-center">Add Employee </h2>
        }
    }

    return (
        <div> <br />
            <div className="container">
                <div className="row">
                    <div className="card col-md-6 offset-md-3 offset-md-3">
                        {
                            title()    // 6. Update
                        }
                        <div className="card-body">
                            <form>

                                <div className="form-group mb-2">
                                    <label className="form-label">First Name: </label>
                                    <input type="text" placeholder="Enter your first name" name="firstname" className="form-control"
                                        value={firstname} onChange={(e) => setFirstName(e.target.value)}></input>
                                </div>

                                <div className="form-group mb-2">
                                    <label className="form-label">Last Name: </label>
                                    <input type="text" placeholder="Enter your last name" name="lastname" className="form-control"
                                        value={lastname} onChange={(e) => setLastName(e.target.value)}></input>
                                </div>

                                <div className="form-group mb-2">
                                    <label className="form-label">Email Id: </label>
                                    <input type="email" placeholder="Enter your Email" name="emailId" className="form-control"
                                        value={emailId} onChange={(e) => setEmailId(e.target.value)}></input>
                                </div>

                                <button className="btn btn-success" onClick={(e) => saveOrUpdateEmployee(e)}>Submit</button>
                                
                                <Link to='/employees' className="btn btn-danger">Cancel</Link>

                            </form>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    )
}

export default AddEmployeeComponent
