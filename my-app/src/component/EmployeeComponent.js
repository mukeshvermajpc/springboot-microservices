import React, { Component } from 'react';
import EmployeeService from '../service/EmployeeService'
class EmployeeComponent extends Component {
    constructor(props) {
        super(props);
        this.state={
            employee:{},
            department:{},
            organization:{}
        }
    }
    componentDidMount(){
        EmployeeService.getEmployee().then((response)=>{
         this.setState({employee: response.data.employee})
         this.setState({department: response.data.department})
         this.setState({organization: response.data.organization})

         console.log('employee: ',this.state.employee);
         console.log('department: ',this.state.department);
         console.log('organization: ',this.state.organization);
        })
    }
    render() {
        return (
            <div>
                <br/><br/>
              <div class="card col-md-6 offset-md-3">
                <h3 className='text-center card-header'>View Employee Details</h3>
                <div class='card-body d-flex flex-column'>
                    <div class='d-flex flex-row p-2'>
                        <div><strong>Employee First Name: </strong></div>
                        <div>{this.state.employee.firstName}</div>
                    </div>
                    <div class='d-flex flex-row p-2'>
                        <div><strong>Employee Last Name: </strong></div>
                        <div> {this.state.employee.lastName}</div>
                    </div>
                    <div class='d-flex flex-row p-2'>
                        <div><strong>Employee Email:</strong></div>
                        <div>{this.state.employee.email}</div>
                    </div>
                </div>
                <h3 className='text-center card-header'>View Department Details</h3>
                <div class='card-body d-flex flex-column'>
                    <div class='d-flex flex-row p-2'>
                        <div><strong>Department Name: </strong></div>
                        <div>{this.state.department.departmentName}</div>
                    </div>
                    <div class='d-flex flex-row p-2'>
                        <div ><strong>Department Description: </strong></div>
                        <div> {this.state.department.departmentDescription}</div>
                    </div>
                    <div class='d-flex flex-row p-2'>
                        <div><strong>Department Code:</strong></div>
                        <div>{this.state.department.departmentCode}</div>
                    </div>
                </div>
                <h3 className='text-center card-header'>View Organization Details</h3>
                <div class='card-body d-flex flex-column'>
                    <div class='d-flex flex-row p-2'>
                        <div><strong>Organization Name: </strong></div>
                        <div>{this.state.organization.organizationName}</div>
                    </div>
                    <div class='d-flex flex-row p-2'>
                        <div ><strong>Organization Description: </strong></div>
                        <div> {this.state.organization.organizationDescription}</div>
                    </div>
                    <div class='d-flex flex-row p-2'>
                        <div><strong>Organization Code:</strong></div>
                        <div>{this.state.organization.organizationCode}</div>
                    </div>
                </div>
                </div>  
            </div>
        );
    }
}

export default EmployeeComponent;