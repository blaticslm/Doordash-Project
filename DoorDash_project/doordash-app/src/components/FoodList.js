import React, {Component, useState,useEffect} from 'react';
import {addItemToCart, getMenu, getRestaurants} from "../utils";
import {Button, Select, Tooltip, message, List, Card} from "antd";
import {PlusOutlined} from "@ant-design/icons"

const {Option} = Select

const AddToCartButton = ({itemId}) => { //{itemId}， 我需要传入的是object的数值而不是object本身
    const[loading, setLoading] = useState(false);

    const AddToCart = () =>{
        setLoading(true);
        addItemToCart(itemId)
            .then(() => {
                message.success("Successfully add item")
            })
            .catch(err => {
                message.error(err.message)
            })
            .finally(() => {
                setLoading(false);
            })
    }

    return (
        <Tooltip title ="Add to shopping cart">
            <Button loading={loading} type="primary" icon={<PlusOutlined />} onClick={AddToCart} />
        </Tooltip>
    )
}

const FoodList =()=> {
    const [loading, setLoading] = useState(false);
    const [restaurants, setRestaurants] = useState([]);
    const [curRest, setCurRest] = useState();
    const [loadingRest, setLoadingRest] = useState(false);
    const [foodData, setFoodData] = useState([]);

    useEffect(() => {
        setLoadingRest(true);
        getRestaurants()
            .then(data => {
                setRestaurants(data);
            })
            .catch(err => {
                message.error(err.message);
            })
            .finally(() => {
                setLoadingRest(false);
            })
    }, [])

    useEffect(() => {
        if(curRest) {
            setLoading(true);
            getMenu(curRest)
                .then(data => {
                    setFoodData(data);
                })
                .catch(err => {
                    message.error(err.message);
                })
                .finally(() => {
                    setLoading(false);
                })
        }
    },[curRest])

    return(
        <>
            <Select restId={curRest}
                    onSelect={restId => setCurRest(restId)}
                    placeholder="Select a restaurant"
                    loading={loadingRest}
                    style={{width:300}}
                    onChange={() => {}}
            >
                {restaurants.map((item) => {
                    return <Option value={item.id}>{item.name}</Option>
                })}
            </Select>
            {curRest && (
                <List
                    style ={{marginTop:20}}
                    loading={loading}
                    grid={{
                        gutter:16,
                        xs:1,
                        sm:2,
                        md:4,
                        lg:4,
                        xl:3,
                        xxl:3,
                    }}
                    dataSource={foodData}
                    renderItem={(item) => (
                        <List.Item>
                            <Card title={item.name} extra={<AddToCartButton itemId={item.id} />}>
                                <img
                                    src={item.imageUrl}
                                    alt={item.name}
                                    style={{ height: 340, width: "100%", display: "block" }}/>
                                {`Price: ${item.price}`}
                            </Card>
                        </List.Item>)
                } />
            )}
        </>
    )
}


export default FoodList;