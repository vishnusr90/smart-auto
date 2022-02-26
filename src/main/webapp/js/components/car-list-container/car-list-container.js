import { buyCar, deleteCar, restockCar } from '../../../service/car-service.js';
import { getCurrentRole } from '../../../service/user-service.js';
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
                <th>Color</th>
                <th>Price</th>
                <th class="hide">Remaining</th>
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
        await this.init(htmlTemplate());
        this.roles = await getCurrentRole();

        if (this.roles[0].authority === 'ROLE_ADMIN') {
            this.$$('.hide').classList.remove('hide');
        }
        await this.$$('car-list').load(this.roles);
    }

    loadEventListeners() {
        this.on('car-list', 'delete-car', async (e) => {
            const carId = e.detail.id;
            await deleteCar(carId);
            await this.$$('car-list').load(this.roles);
        });

        this.on('car-list', 'buy-car', async (e) => {
            const carId = e.detail.id;
            await buyCar(carId);
            await this.$$('car-list').load(this.roles);
        });

        this.on('car-list', 'restock-car', async (e) => {
            const carId = e.detail.id;
            await restockCar(carId);
            await this.$$('car-list').load(this.roles);
        });
    }
}

window.customElements.define('car-list-container', CarListContainer);