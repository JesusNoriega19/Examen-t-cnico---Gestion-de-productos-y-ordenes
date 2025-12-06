import { Component, OnInit } from "@angular/core";
import { OrdenesService } from "../ordenes.service";
import { ProductosService } from "../productos.service";

@Component({
  selector: 'app-crear-orden',
  templateUrl: './crear-orden.component.html'
})
export class CrearOrdenComponent implements OnInit {

  productos: any[] = [];
  productosSeleccionados: any[] = [];
  cliente = '';

  constructor(
    private prodService: ProductosService,
    private ordenService: OrdenesService
  ) {}

  ngOnInit() {
    this.prodService.listar().subscribe(p => this.productos = p);
  }

  agregar(prod: any) {
    this.productosSeleccionados.push({
      idProducto: prod.idProducto,
      cantidad: 1
    });
  }

  crear() {
    const request = {
      cliente: this.cliente,
      productos: this.productosSeleccionados
    };

    this.ordenService.crearOrden(request).subscribe(() => {
      alert('Orden Creada!');
      this.productosSeleccionados = [];
      this.cliente = '';
    });
  }

}
