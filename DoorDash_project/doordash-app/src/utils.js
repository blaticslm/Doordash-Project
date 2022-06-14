//login APi
//因为不是default，所以每次都得是实例化
export const login = (credential) => { //公开之后可以直接引用, 回调函数
    //credential : {username: value, password:value}
    //request option: url, http method, data
    const{username, password} = credential;  //step 1: get login info
    const loginUrl = `/login?username=${username}&password=${password}`; //step 2: organize my login url
    //注意：一般时候用户名和密码不会直接放url上，通常都是加密，要不然GG

    //以下是获取。 https://developer.mozilla.org/en-US/docs/Web/API/fetch#parameters
    //一般来说不怎么用fetch，因为兼容性不太行
    return fetch(loginUrl, { //3, send request
        method:"POST",
        headers: {
            "Content-Type":"application/json",
        },
        credentials:"include"

    }).then( response => { //4, receive backend response
        console.log(response)
        //case 1: login successful
        //case 2: login fail
        if(response.status < 200 || response.status >= 300) {
            throw Error("Fail to log in"); //直接login fail问题
        }
    })
}

export const signup = (new_user_data) => {
    const signupUrl = `/signup`; //postman signup web

    return fetch(signupUrl, {
        method:"POST",
        headers: {
            "Content-Type":"application/json",
        },
        body: JSON.stringify(new_user_data),
    }).then(response => {
        if(response.status < 200 || response.status >= 300) {
            throw Error("Sign Up Fail!");
        }
    })
}

export const getCart = () => {
    return fetch("/cart").then((response) => {
        if (response.status < 200 || response.status >= 300) {
            throw Error("Fail to get shopping cart data");
        }

        return response.json(); //get method无需post那样需要网址。相反，直接fetch直接获取
    })
}

export const checkout = () => {
    return fetch("/checkout").then((response) => {
        if (response.status < 200 || response.status >= 300) {
            throw Error("Fail to checkout");
        }
    });
};

//menu,restaurants, add to cart\
export const getMenu = (restId) => {
    return fetch(`restaurant/${restId}/menu`).then(response => {
        if (response.status < 200 || response.status >= 300) {
            throw Error("Fail to get menus");
        }

        return response.json();
    })
}

export const getRestaurants = () => {
    return fetch(`restaurant/`).then(response => {
        if (response.status < 200 || response.status >= 300) {
            throw Error("Fail to get restaurant");
        }

        return response.json();
    })
}

export const  addItemToCart = (menuId) => {

    const addItemUrl = `/order/${menuId}`; //step 2: organize my login url

    return fetch(addItemUrl,{
        method:"POST",
        headers:{
            "Content-Type":"application/json"
        },
        credentials:"include"
    } ).then(response => {
        if (response.status < 200 || response.status >= 300) {
            throw Error("Fail to add item to shipping cart");
        }

    })
}




