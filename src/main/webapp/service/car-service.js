import { del, get, post } from '../js/http-client.js';

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

export const buyCar = async (carId) => await post(`/smart-auto/api/sales/cars/buy/${carId}`);

export const restockCar = async (carId) => await post(`/smart-auto/api/cars/restock/${carId}`);

export const deleteCar = async (carId) =>  await del(`/smart-auto/api/cars/${carId}`);

export const addNewCar = async (carDetails) =>  await post(`/smart-auto/api/cars/`, carDetails);