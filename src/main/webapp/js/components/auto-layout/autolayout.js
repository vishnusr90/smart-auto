import { BaseComponent } from "../../base-component";




export class AutoLayout extends BaseComponent {
    constructor() {
        super();
        this.load();
    }

    load() {
        
    }
}

window.customElements.define('auto-layout', AutoLayout);