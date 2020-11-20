const BASE_URL = "http://localhost:8080";   //izmeni ova dva
const API_BASE: string = `${BASE_URL}/api/cultural_offers`; //tako da se u evirontment podesi

export const API_FILTER: string = `${API_BASE}/filter`;
export const API_FILTER_NAMES: string = `${API_BASE}/filter_names`;
export const API_FILTER_LOCATIONS: string = `${API_BASE}/filter_locations`;
export const API_FILTER_TYPES: string = `${API_BASE}/filter_types`;
export const API_FILTER_FOLLOWINGS: string = `${API_BASE}/filter_followings`;
export const API_TOGGLE_SUBSCRIPTION: string = `${API_BASE}/toggle_subscription`;