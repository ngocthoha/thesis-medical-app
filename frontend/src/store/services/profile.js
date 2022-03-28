// import axios from "axios";
// const url = "http://13.40.75.147:8080";

// function profile_list(params) {
// 	return axios.get(`${url}/api/profiles`,params,{headers:
//         {
//             Authorization: 'Bearer ' + params.token
//         }}).then(response => {
// 		return {
// 			data: response.data
// 		};
// 	});
// }

// export default {
//     profile_list,
// }

import axios from "axios";

const url = "http://13.40.75.147:8080";

function profile_list(params) {
    axios.defaults.headers.common = {'Authorization': `Bearer ${params.token}`};
	return axios.get(`${url}/api/profiles`,{}).then(response => {
		return {
			data: response.data
		};
	});
}

export default {
    profile_list,
}