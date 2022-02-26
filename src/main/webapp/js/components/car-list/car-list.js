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
    <tr id="car-row" data-remaining=${car.remaining}>
        <td>${car.brand}</td>
        <td>${car.model}</td>
        <td>${car.year}</td>
        <td>${car.color}</td>
        <td>$ ${car.price}</td>
        <td id="remaining" class="hide">${car.remaining}</td>
        <td><button id=${car.id} class="buy">Buy Now</button></td>
        <td id="delete"><button id=${car.id} class="delete">Delete</button></td>
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
        console.log('connected back');
        this.loadEventListeners();
    }

    async load(roles) {
        console.log('roles ' , roles);
        let carList = await loadAllCars();
        console.log('cars ', carList);
        this.init(htmlTemplate(carList));

        if (roles[0].authority === 'ROLE_ADMIN') {
            console.log('is an admin');
            this.$$$('.buy').forEach(b => b.classList.add('hide'));
            this.$$$('#remaining').forEach(r => r.classList.remove('hide'));
        } else {
            console.log('is a buyer');
            const remaining = this.$$('#car-row').dataset.remaining;
            this.$$$('.delete').forEach(b => b.classList.add('hide'));
        }
    }

    loadEventListeners() {
        console.log('event listeners');
        this.on('button', 'click', (e) => {
            const buttonType = e.target.classList;
            console.log(buttonType);
            const carId = e.target.getAttribute('id').split('-')[0];

            if (buttonType.value === 'delete') {
                this.triggerEvent('delete-car', {id: carId});
            } else {
                this.triggerEvent('buy-car', {id: carId})
            }
            
        });
    }


}

window.customElements.define('car-list', CarList);