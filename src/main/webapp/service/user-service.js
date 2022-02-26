import { get } from '../js/http-client.js';

export const getCurrentRole = async () => await get('/smart-auto/api/users/role');