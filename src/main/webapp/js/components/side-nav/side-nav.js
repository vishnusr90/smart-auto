import { BaseComponent, html} from '../../base-component.js';

const htmlTemplate = () => {
    return html`
        <div style="padding-bottom: 20px;">
            <button id="add-car-button" style="font-size: 16px;">Add New Car</button>
        </div>

        <div style="padding-bottom: 20px;">
            <button id="sales-button" style="font-size: 16px;">Sales</button>
        </div>

        <div>
            <button id="logout" style="font-size: 16px;">Logout</button>
        </div>
    `;
};

export class SideNav extends BaseComponent {
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
        this.on('#add-car-button', 'click', () => {
            return location.href = '/smart-auto/add-car.html';
        });

        this.on('#logout', 'click', () => {
            return location.href = '/smart-auto/logout';
        });
    }
}

window.customElements.define('side-nav', SideNav);