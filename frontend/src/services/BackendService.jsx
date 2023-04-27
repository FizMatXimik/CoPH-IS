import axios from 'axios'
const API_URL = 'http://localhost:8072'
// const AUTH_URL = 'http://localhost:8081/auth'

class BackendService {

//  login(login, password) {
//  return axios.post(`${AUTH_URL}/login`, {login, password})
//  }
//  logout() {
//  return axios.get(`${AUTH_URL}/logout`)
//  }

/* Companies */

    retrieveAllCompanies(search, page, limit) {
        return axios.get(`${API_URL}/data-service/v1/company/search?search=${search}&page=${page}&companies_per_page=${limit}`);
    }

    deleteCompany(id) {
        return axios.delete(`${API_URL}/data-service/v1/company/${id}`);
    }

}
export default new BackendService()