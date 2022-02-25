import { loadAllCars } from '../../../service/car-service.js';
import { BaseComponent, html} from '../../base-component.js';

const htmlTemplate = (carList) => {
    if (carList.length == 0) {
        return html``;
    }
    return html`
        <table>
        ${carList.map(car => carRow(car))}
        </table>    
    `;
 };

const carRow = (car) => html`
    <link rel='stylesheet' type='text/css' href='/smart-auto/js/components/car-list/car-list.css'>
    <tr id=${car.id}>
        <td>${car.brand}</td>
        <td>${car.model}</td>
        <td>${car.year}</td>
        <td>${car.topSpeed}</td>
        <td>$ ${car.price}</td>
        <td>${car.status}</td>
        <td><button id=${car.id}>Delete</button></td>
    </tr>
`;

const renderButton = () => {
    return html`
        
    `;
};

export class CarList extends BaseComponent {
    constructor() {
        super();
    }
    
    connectedCallback() {
        this.loadEventListeners();
    }

    async load() {
        const carList = await loadAllCars();
        this.init(htmlTemplate(carList));
    }

    loadEventListeners() {
        this.on('button', 'click', (e) => {
            this.triggerEvent('delete-car', {id: e.target.getAttribute('id')})
        });
    }


}

window.customElements.define('car-list', CarList);