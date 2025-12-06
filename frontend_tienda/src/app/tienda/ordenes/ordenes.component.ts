import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, FormArray, Validators } from '@angular/forms';
import { OrdenesService } from '../ordenes.service';

@Component({
  selector: 'app-ordenes',
  templateUrl: './ordenes.component.html',
  styleUrls: ['./ordenes.component.css']
})
export class OrdenesComponent implements OnInit {

  crearOrdenForm: FormGroup;
  consultarOrdenForm: FormGroup;
  ordenes: any[] = [];

  constructor(private fb: FormBuilder, private ordenesService: OrdenesService) {
    // Formulario para crear orden
    this.crearOrdenForm = this.fb.group({
      cliente: ['', Validators.required],
      productos: this.fb.array([])
    });

    // Formulario para consultar ordenes
    this.consultarOrdenForm = this.fb.group({
      cliente: ['', Validators.required],
      fechaInicio: ['', Validators.required],
      fechaFin: ['', Validators.required]
    });
  }

  ngOnInit(): void {
    // Agregar un producto inicial al formulario
    this.agregarProducto();
  }

  // Obtener el FormArray de productos
  get productos() {
    return this.crearOrdenForm.get('productos') as FormArray;
  }

  agregarProducto() {
    this.productos.push(this.fb.group({
      idProducto: ['', Validators.required],
      cantidad: [1, Validators.required]
    }));
  }

  eliminarProducto(index: number) {
    this.productos.removeAt(index);
  }

  // Crear una orden
  crearOrden() {
    if (this.crearOrdenForm.invalid) return;

    this.ordenesService.crearOrden(this.crearOrdenForm.value).subscribe({
      next: (idOrden: number) => {
        alert(`Orden creada con ID: ${idOrden}`);
        this.crearOrdenForm.reset();
        this.productos.clear();
        this.agregarProducto();
      },
      error: (err) => console.error('Error al crear orden:', err)
    });
  }

  // Consultar ordenes
  consultarOrdenes() {
    if (this.consultarOrdenForm.invalid) return;

    this.ordenesService.consultarOrdenes(this.consultarOrdenForm.value).subscribe({
      next: (res: any[]) => {
        this.ordenes = res;
      },
      error: (err) => console.error('Error al consultar ordenes:', err)
    });
  }
}
