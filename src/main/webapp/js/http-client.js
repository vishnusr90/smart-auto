
export const get = (url, cache = false) => {
    return fetchData(url, {
        method: 'GET',
        cache: cache ? 'cache-subsequent' : 'no-cache'
    })
};

export const del = (url, cache = false) => {
    return fetchData(url, {
        method: 'DELETE',
        cache: cache ? 'cache-subsequent' : 'no-cache'
    })
};

export const post = (url, data = {}) => {
    return fetchData(url, {
        method: 'POST',
        headers: {'Content-Type': 'application/json'},
        body: stringify(data)
    })
};

async function fetchData(url, options = {}) {
    return fetch(url, options).then(handleError).then(asJson);
}

const fetch = async (url, options = {}) => {
    const request = new Request(url, options);
    const response = await window.fetch(request);
    if (response.status == 401) {
        location.href =  '/index.html';
    }
    return response;
};

const asJson = async (response) => {
    const context = await response.text();
    return context.length ? JSON.parse(context) : {};
};

const handleError = async (response) => {
    if(response.ok) {
        return response;
    }

    const error = await asJson(response);
    return Promise.reject(error);
};

const stringify = (data = {}) => {
    const type = typeof data;
    const primitive = ['string', 'number', 'boolean', 'undefined'].includes(type);
    return primitive ? data : JSON.stringify(data);  
};