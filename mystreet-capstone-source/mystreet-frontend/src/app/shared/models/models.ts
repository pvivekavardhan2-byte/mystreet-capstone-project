export interface Product { id:string; name:string; brand:string; description:string; price:number; imageUrl:string; sizesCsv:string; stockQty:number; }
export interface AuthResponse { token:string; userId:string; email:string; admin:boolean; }
export interface CartItem { product:Product; size:string; quantity:number; }
export interface OrderItemRequest { productId:string; size:string; quantity:number; }
export interface OrderRequest { items:OrderItemRequest[]; shippingAddress:string; paymentMode:'COD'|'MOCK_UPI'|'MOCK'; }
export interface OrderResponse { id:string; status:string; paymentMode:string; totalAmount:number; shippingAddress:string; createdAt:string; items:{productId:string;productName:string;size:string;quantity:number;price:number}[]; }
