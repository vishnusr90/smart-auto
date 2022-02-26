import '../js/components/car-list-container/car-list-container.js';
import { getCurrentRole } from './user-service.js';

(async() => {
    console.log('this');
    const container = document.querySelector('car-list-container');
    container.load();
})();