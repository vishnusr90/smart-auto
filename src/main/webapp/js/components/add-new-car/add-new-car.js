import { addNewCar } from '../../../service/car-service.js';
import { BaseComponent, html} from '../../base-component.js';

const htmlTemplate = () => {
    return html`
    <link rel="stylesheet" type="text/css" href="/smart-auto/js/components/add-new-car/add-new-car.css">
        <div style="padding-left: 25%;">
            <div class="row">
                <div class="heading">Brand</div>  
                <input id="brand" type="text" placeholder="Enter brand of car"/>  
            </div>
            
        
            <div class="row">
                <div class="heading">Model</div>  
                <input id="model" type="text" placeholder="Enter model of car"/>
            </div>

            <div class="row">
                <div class="heading">Year</div>  
                <input id="year" type="text" placeholder="Enter year of car"/>
            </div>

            <div class="row">
                <div class="heading">Color</div>  
                <input id="color" type="text" placeholder="Enter color of car"/></br>
            </div>

            <div class="row">
                <div class="heading">Price</div>  
                <input id="price" type="number" placeholder="Enter price of car"/></br>
            </div>

            <input id="add-car" type="submit" value="Add"/>  
        </div>
    `;
};

export class NewCarComponent extends BaseComponent {
    constructor() {
        super();
        (async  () => {
            await this.load();
        })();
    }

    async load() {
        await this.init(htmlTemplate());
        this.loadEventListeners();
    }

    loadEventListeners() {
        this.$$('#add-car').addEventListener('click', async () => {
            const brand = this.$$('#brand').value || '';
            const model = this.$$('#model').value || '';
            const year = this.$$('#year').value || '';
            const color = this.$$('#color').value || '';
            const price = this.$$('#price').value || '';
            const details = {
                brand,
                model,
                year,
                color,
                price
            };
            try {
                const res = await addNewCar(details);
                if (res instanceof ErrorEvent) {
                    alert('Error in saving. Please try again later.');
                } else {
                    alert('New car added');
                }
            } catch(e) {
                alert('Please enter all the fields');
            }
        });
    }
}

window.customElements.define('add-new-car', NewCarComponent);