import { del, get } from '../js/http-client.js';

const FETCH_CARS = '/smart-auto/api/cars/';

export const fetchCars = () => get(FETCH_CARS); 

export const loadAllCars = async () => await fetchCars();

export const deleteCar = async (carId) => {
    await del(`/smart-auto/api/admin/car/delete/${carId}`);
}