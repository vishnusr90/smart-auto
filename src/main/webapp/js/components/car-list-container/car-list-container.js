import { deleteCar, loadAllCars } from '../../../service/car-service.js';
import { BaseComponent, html} from '../../base-component.js';
import '../car-list/car-list.js';

const htmlTemplate = () => {
    return html`
        <link rel='stylesheet' type='text/css' href='/smart-auto/js/components/car-list-container/car-list-container.css'>
        <div style="width: 100%;">
        <table>
            <tr>
                <th>Brand</th>
                <th>Model</th>
                <th>Year</th>
                <th>Top Speed (km/hr)</th>
                <th style="width: 50px;">Price</th>
                <th>Status</th>
                <th></th>
            </tr>
        </table>
        </div>
        <car-list></car-list>
    `;
};

export class CarListContainer extends BaseComponent {

    constructor() {
        super();
    }

    connectedCallback() {
        this.loadEventListeners();
    }

    async load() {
        console.log('inside container');
        await this.init(htmlTemplate());
        await this.$$('car-list').load();
    }

    loadEventListeners() {
        this.on('car-list', 'delete-car', async (e) => {
            const carId = e.detail.id;
            console.log('Deleting carid ', carId);
            await deleteCar(carId);
            console.log('deleted car !!!!!', carId);
            await this.$$('car-list').load();
        });
    }
}

window.customElements.define('car-list-container', CarListContainer);