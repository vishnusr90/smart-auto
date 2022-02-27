import { loadAllCars } from '../../../service/car-service.js';
import { BaseComponent, html} from '../../base-component.js';

const htmlTemplate = (carList) => {
    if (carList.length == 0) {
        return html`<div></div>`;
    }
    return html`
        <table>
            ${carList.map(car => carRow(car))}
        </table>    
    `;
 };

const carRow = (car) => html`
    <link rel='stylesheet' type='text/css' href='/smart-auto/js/components/car-list/car-list.css'>
    <tr id="car-row" data-remaining=${car.remaining}>
        <td>${car.brand}</td>
        <td>${car.model}</td>
        <td>${car.year}</td>
        <td>${car.color}</td>
        <td>$ ${car.price}</td>
        <td id="remaining" class="hide">${car.remaining}</td>
        ${renderBuyButton(car)}
        <td id="delete"><button id=${car.id} class="delete">Decrease Stock</button></td>
        <td id="re-stock"><button id=${car.id} class="re-stock">Increase Stock</button></td>
    </tr>
`;

const renderBuyButton = (car) => {
    if (car.remaining > 0) {
        return html`<td><button id=${car.id} class="buy">Buy Now</button></td>`;
    }
    return html`<td></td>`;
};

export class CarList extends BaseComponent {
    constructor() {
        super();
    }
    
    connectedCallback() {
        this.loadEventListeners();
    }

    async load(roles) {
        let carList = await loadAllCars();
        this.init(htmlTemplate(carList));

        if (roles[0].authority === 'ROLE_ADMIN') {
            this.$$$('.buy').forEach(b => b.classList.add('hide'));
            this.$$$('.out-of-stock').forEach(b => b.classList.add('hide'));
            this.$$$('#remaining').forEach(r => r.classList.remove('hide'));
        } else {
            this.$$$('.delete').forEach(b => b.classList.add('hide'));
            this.$$$('.re-stock').forEach(b => b.classList.add('hide'));
        }
    }

    loadEventListeners() {
        this.on('button', 'click', (e) => {
            const buttonType = e.target.classList.value || '';
            const carId = e.target.getAttribute('id').split('-')[0];

            switch(buttonType) {
                case 'delete':
                    this.triggerEvent('delete-car', {id: carId});
                    break;
                case 'buy':
                    this.triggerEvent('buy-car', {id: carId})
                    break;
                case 're-stock': 
                    this.triggerEvent('restock-car', {id: carId})
                    break;
            }
        });
    }
}

window.customElements.define('car-list', CarList);