import {Component, OnInit} from '@angular/core';
import {FormBuilder, FormGroup} from "@angular/forms";

@Component({
  selector: 'app-calculator',
  templateUrl: './calculator.component.html',
  styleUrls: ['./calculator.component.css']
})
export class CalculatorComponent implements OnInit {

  result: number;
  calculatorForm: FormGroup;

  constructor(private formBuilder: FormBuilder) {
    this.calculatorForm = formBuilder.group(
      {
        x: null,
        y: null
      }
    )
  }

  ngOnInit() {
  }

  onSubmit(formValue) {
    this.result = formValue.x + formValue.y;
  }
}
