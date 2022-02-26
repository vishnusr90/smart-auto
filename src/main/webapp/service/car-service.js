import { del, get, post } from '../js/http-client.js';

const FETCH_CARS = '/smart-auto/api/cars/';


export const loadAllCars = async () => {
    const cars = await get('/smart-auto/api/cars/');
    let result = [];
    for(let car of cars) {

        if (car.remaining === 0) {
            car.remaining = 'Restock !';
            car.disableBuyButton = true;
        }
        result.push(car)
    }
    return result;
}

export const buyCar = async (carId) => await post(`/smart-auto/api/car/buy/${carId}`);

export const restockCar = async (carId) => await post(`/smart-auto/api/restock/car/${carId}`);

export const deleteCar = async (carId) =>  await del(`/smart-auto/api/car/${carId}`);

export const addNewCar = async (carDetails) =>  await post(`/smart-auto/api/car`, carDetails);