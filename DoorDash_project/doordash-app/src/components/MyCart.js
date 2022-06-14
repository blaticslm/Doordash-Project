import {useEffect, useState} from "react";
import {List, Button, Drawer, Typography, message} from "antd";
import {checkout, getCart} from "../utils";

const{Text} = Typography;

const MyCart = () =>{
    const [cartVisible, setCartVisible] = useState(false);
    const [cartData, setCartData] = useState();
    const [loading, setLoading] = useState(false);
    const [checking, setChecking] = useState(false);

    useEffect(() => {
        if(!cartVisible) {
            return;
        }

        setLoading(true);
        console.log("geting cart");
        getCart()
            .then((data) => {
                setCartData(data);
            })
            .catch(err => {
                message.error(err.message);
            })
            .finally(() => {
                setLoading(false);
            });
    }, [cartVisible]);

    const onCheckOut = () => {
        setChecking(true);
        checkout()
            .then(() => {
                message.success("Successfully checkout");
                setCartVisible(false);
            })
            .catch((err) => {
                message.error(err.message);
            })
            .finally(() => {
                setChecking(false);
            });
    };

    const onOpenDrawer = () => {
        setCartVisible(true);
    }

    const onCloseDrawer = () => {
        setCartVisible(false);
    }

    return(
        <>
            <Button type = "primary" shape="round" onClick={onOpenDrawer} location>
                cart
            </Button>
            <Drawer
                title="My Shopping Cart"
                onClose={onCloseDrawer}
                visible={cartVisible}
                width={520}
                footer={
                    <div style={{display:"flex", justifyContent:"space-between"}}>
                        <Text Strong={true}>{`Total Price: $${cartData?.totalPrice}`}</Text>
                        <div>
                            <Button onClick={onCloseDrawer} style={{ marginRight: 8 }}>
                                Cancel
                            </Button>

                            <Button
                                onClick={onCheckOut}
                                type="primary"
                                loading={checking}
                                disable={loading || cartData?.orderItemList === 0}
                            >
                                Checkout
                            </Button>
                        </div>
                    </div>
                }
            >
                <List
                    loading={loading}
                    itemLayout="horizontal"
                    dataSource={cartData?.orderItemList}
                    renderItem={item => (
                    <List.Item>
                        <List.Item.Meta
                            title={item.menuItem.name}
                            description={`$${item.price}`}
                        />
                        </List.Item>
                    )}
                />
            </Drawer>
        </>
    )
}

export default MyCart;