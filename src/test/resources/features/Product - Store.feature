#language: es
@testfeature
Característica: Product - Store
  @test
  Escenario: Validación del precio de un producto
    Dado estoy en la página de la tienda
    Y me logueo con mi usuario "sonigab11@gmail.com" y clave "soniagabriela12345"
    Cuando navego a la categoria "Clothes" y subcategoria "Men"
    Y agrego 2 unidades del primer producto al carrito
    #//img[@src='https://qalab.bensg.com/store/1-home_default/hummingbird-printed-t-shirt.jpg']
    #//input[@id='quantity_wanted']    es el cuadrito de cantidad este tiene que cambiar a 2
    #//button[@class='btn btn-primary add-to-cart']  añadir al carrito
    Entonces Entonces valido en el popup la confirmación del producto agregado
    #//h4[@class='modal-title h6 text-sm-center']    tiene que decir: Producto añadido correctamente a su carrito de compra
    Y valido en el popup que el monto total sea calculado correctamente
    #//p[@class='product-total']   tiene que decir: S/ 38.24
    Cuando finalizo la compra
    #//a[@href='//qalab.bensg.com/store/pe/carrito?action=show']
    Entonces valido el titulo de la pagina del carrito
    #//h1[@class='h1']  tiene que ser igual a CARRITO
    Y vuelvo a validar el calculo de precios en el carrito
    #//div[@class='cart-summary-line cart-total'] TIENE QUE DECIR: S/ 38.24