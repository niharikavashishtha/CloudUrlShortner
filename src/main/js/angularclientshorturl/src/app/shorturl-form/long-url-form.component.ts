import { Component, OnInit  } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { LongUrl } from '../model/long-url';
import { LongUrlService } from '../service/long-url.service';

@Component({
  selector: 'app-long-url-form',
  templateUrl: './long-url-form.component.html',
  styleUrls: ['./long-url-form.component.css']
})
export class LongUrlFormComponent implements OnInit{

   longUrl: LongUrl;
   shortUrl : string;
   submitted = false;

    constructor(private route: ActivatedRoute, private router: Router, private longUrlService: LongUrlService) {
      this.newLongUrl();
    }

    onSubmit() {
    console.log('calling shortenedurl service');
      this.longUrlService.save(this.longUrl).subscribe((data : LongUrl) => {
         console.log("received short url" + data);
         this.shortUrl = data.shortUrl;
         //this.shortUrl = this.longUrl.apiKey;
         this.submitted = true;
      },
      (error: Error) => console.error(error)
      );
    }

    ngOnInit(): void {
      }

     newLongUrl(): void {
     this.longUrl = new LongUrl();
        this.submitted = false;
        this.longUrl.longUrl = '';
        this.longUrl.apiKey = '';
        this.shortUrl = '';
        this.longUrl.clientId = 0;

      }
}
