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

            <input id="add-car" type="submit" value="Add"/>  
        </div>
        
    `;
};

export class NewCarComponent extends BaseComponent {
    constructor() {
        super();
        console.log('constructor');
        (() => {
            this.load();
        })();
    }

    load() {
        this.init(htmlTemplate());
        this.loadEventListeners();
    }

    loadEventListeners() {
        
        this.$$('#add-car').addEventListener('click', async () => {
            const brand = this.$$('#brand').value;
            const model = this.$$('#model').value;
            const year = this.$$('#year').value;
            const color = this.$$('#color').value;
            const details = {
                brand,
                model,
                year,
                color
            };
            console.log(details);
            const res = await addNewCar(details);
            console.log(res);
        });
    }
}

window.customElements.define('add-new-car', NewCarComponent);