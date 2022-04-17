import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { ClientEntityService } from '../service/client-entity.service';
import { ClientEntity } from '../model/client-entity';

@Component({
  selector: 'app-client-entity-form',
  templateUrl: './client-entity-form.component.html',
  styleUrls: ['./client-entity-form.component.css']
})
export class ClientEntityFormComponent implements OnInit{

    clientEntity: ClientEntity;
    submitted = false;

    constructor(private route: ActivatedRoute, private router: Router, private clientEntityService: ClientEntityService) {
      this.clientEntity = new ClientEntity();
    }

    onSubmit() {
    console.log('calling clientEntity');
      this.clientEntityService.save(this.clientEntity).subscribe((data : ClientEntity) => {
                                                               this.clientEntity = data;
                                                               this.submitted = true;
                                                            },
                                                                   (error: Error) => console.error(error));
      console.log('called clientEntity');
    }

    ngOnInit(): void {
          }

    setClientEntityDefault(): void {
            this.clientEntity.clientName = '';
            this.clientEntity.clientEmail = '';
            this.clientEntity.clientAccountNumber = '';
            this.submitted = false;
          }

}
