import { Component, OnInit } from '@angular/core';
import { ProductosService } from '../productos.service';
import { FormBuilder, FormGroup } from '@angular/forms';

@Component({
  selector: 'app-productos',
  templateUrl: './productos.component.html'
})
export class ProductosComponent implements OnInit {

  productos: any[] = [];
  form!: FormGroup;
  modoEdicion = false;
  idEditando: number | null = null;

  constructor(
    private service: ProductosService,
    private fb: FormBuilder
  ) {}

  ngOnInit() {
    this.form = this.fb.group({
      idProducto: [''],
      nombre: [''],
      precio: ['']
    });

    this.cargarProductos();
  }

  cargarProductos() {
    this.service.listar().subscribe(data => this.productos = data);
  }

  guardar() {
    const producto = this.form.value;

    if (this.modoEdicion && this.idEditando != null) {
      this.service.actualizar(this.idEditando, producto).subscribe(() => {
        this.cancelar();
        this.cargarProductos();
      });
    } else {
      this.service.crear(producto).subscribe(() => {
        this.form.reset();
        this.cargarProductos();
      });
    }
  }

  editar(p: any) {
    this.modoEdicion = true;
    this.idEditando = p.idProducto;
    this.form.patchValue(p);
  }

  cancelar() {
    this.modoEdicion = false;
    this.idEditando = null;
    this.form.reset();
  }

  eliminar(id: number) {
    this.service.eliminar(id).subscribe(() => this.cargarProductos());
  }

}
