import {html as _html, render as _render} from 'https://unpkg.com/lit-html?module';
import {unsafeHTML as _unsafeHTML} from 'https://unpkg.com/lit-html/directives/unsafe-html.js';

export const html = _html;
export const render = _render;
export const unsafeHTML = _unsafeHTML;

export class BaseComponent extends HTMLElement {

    constructor() {
        super();
        this.eventCacheMap = new Map();
        this.renderRoot = document.createElement('content');
        this.attachShadow({mode: 'open'});
        this.shadowRoot.appendChild(this.renderRoot);
    }

    async init(templateResult) {
        render(templateResult, this.renderRoot);
    }

    async loading(templateResult) {
        await this.init(html`<div style="text-align: center">Loading ...</div>`)
    }

    async loadingError() {
        await this.init(html`<div style=""text-align: center">Error Occurred</div>`);
    }

    on(selectors, eventType, listener) {
        const existingListener = this.eventCacheMap.get(selectors +'-'+ eventType);
        if (existingListener === listener.toString()) {
            return;
        }
        this.eventCacheMap.set(selectors +'-'+ eventType, listener.toString());

        const that = this.$$;
        this.renderRoot.addEventListener(eventType, (e) => {
            const ele = e.target;
            if(ele.matches(selectors)){
                listener.apply(that, [e]);
            }
        });
    }

    triggerEvent(name, detail = {}) {
        const event = new CustomEvent(name, {
            detail,
            bubbles: true,
            cancelable: true
        });
        this.dispatchEvent(event);
    }   

    $$(selector) {
        return this.renderRoot.querySelector(selector);
    }

    $$$(selector) {
        return this.renderRoot.querySelectorAll(selector);
    }
}