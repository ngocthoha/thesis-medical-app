import axios from "axios";

const url = "http://127.0.0.1:9090/admin";

function login(params) {
	return axios.post(`${url}/user/login`,params).then(response => {
		return {
			data: response.data
		};
	});
}

export default {
    login,
}