import axios from "axios";
const EMPLOYEE_SERVCE_BASE_URL='http://localhost:9191/api/employees';
const EMPLOYEE_ID=7;

class EmployeeService{
    getEmployee(){
       return axios.get(EMPLOYEE_SERVCE_BASE_URL+'/'+EMPLOYEE_ID);
    }
}
export default new EmployeeService