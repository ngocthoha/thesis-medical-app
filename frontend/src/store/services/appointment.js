
import axios from "axios";

// const url = "http://13.40.75.147:8080";
const url = process.env.VUE_APP_ROOT_API;
function speciality_list(params) {
    axios.defaults.headers.common = {'Authorization': `Bearer ${params.token}`};
	return axios.get(`${url}/api/specialties`,{}).then(response => {
		return {
			data: response.data
		};
	});
}

function getDoctorList_byDateAndSpeciality(params){
	axios.defaults.headers.common = {'Authorization': `Bearer ${params.token}`};
	return axios.get(`${url}/api/schedules/doctors`,{ params: params.data }).then(response => {
		return {
			data: response.data
		};
	});
}

function createAppointment(params){
	axios.defaults.headers.common = {'Authorization': `Bearer ${params.token}`};
	return axios.post(`${url}/api/appointments`,params.data,{
		headers: {
		  "Content-Type": "multipart/form-data",
		},
	  }).then(response => {
		return {
			data: response.data
		};
	});
}

function getAppointment_byDate_Doctor(params){
	axios.defaults.headers.common = {'Authorization': `Bearer ${params.token}`};
	return axios.get(`${url}/api/appointments/doctor`,{ params: params.data }).then(response => {
		return {
			data: response.data
		};
	});
}
export default {
    speciality_list,
	getDoctorList_byDateAndSpeciality,
	createAppointment,
	getAppointment_byDate_Doctor,
}