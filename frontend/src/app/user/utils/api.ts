const BASE_URL = "http://localhost:8080";   //ispravi ovo da bude namesteno u environment.ts
const API_BASE = `${BASE_URL}/auth`;
export const API_LOGIN: string = `${API_BASE}/login`;
export const API_HAS_EMAIL: string = `${API_BASE}/has_email`;
export const API_UPDATE_PROFILE: string = `${API_BASE}/update`;