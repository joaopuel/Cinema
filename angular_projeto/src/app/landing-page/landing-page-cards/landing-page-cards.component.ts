import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-landing-page-cards',
  templateUrl: './landing-page-cards.component.html',
  styleUrls: ['./landing-page-cards.component.css']
})
export class LandingPageCardsComponent implements OnInit {

  constructor() { }

  ngOnInit(): void { }

  imageObject = [
    {
      image: 'https://www.ubuy.com.pa/productimg/?image=aHR0cHM6Ly9tLm1lZGlhLWFtYXpvbi5jb20vaW1hZ2VzL0kvNzE3S0grZjFma0wuX0FDX1NMMTAwMF8uanBn.jpg',
      thumbImage: 'https://www.ubuy.com.pa/productimg/?image=aHR0cHM6Ly9tLm1lZGlhLWFtYXpvbi5jb20vaW1hZ2VzL0kvNzE3S0grZjFma0wuX0FDX1NMMTAwMF8uanBn.jpg',
      title: 'Titanic'
    }, 
    {
      image: 'https://mir-s3-cdn-cf.behance.net/project_modules/max_1200/a10f97108050255.5fb629683accc.jpg',
      thumbImage: 'https://mir-s3-cdn-cf.behance.net/project_modules/max_1200/a10f97108050255.5fb629683accc.jpg',
      title: 'La La Land'
    }, 
    {
      image: 'https://creativereview.imgix.net/content/uploads/2019/12/joker_full.jpg?auto=compress,format&q=60&w=1012&h=1500',
      thumbImage: 'https://creativereview.imgix.net/content/uploads/2019/12/joker_full.jpg?auto=compress,format&q=60&w=1012&h=1500',
      title: 'Joker'
    },
    {
      image: 'https://static01.nyt.com/images/2017/09/15/arts/24movie-posters8/24movie-posters8-superJumbo.jpg',
      thumbImage: 'https://static01.nyt.com/images/2017/09/15/arts/24movie-posters8/24movie-posters8-superJumbo.jpg',
      title: 'The Shining'
    }, 
    {
      image: 'https://i.pinimg.com/originals/25/6b/64/256b645267f77d2b93cacda9609111c3.jpg',
      thumbImage: 'https://i.pinimg.com/originals/25/6b/64/256b645267f77d2b93cacda9609111c3.jpg',
      title: 'Halloween'
    }, 
    {
      image: 'https://i.pinimg.com/736x/1c/7e/45/1c7e45a1ba3fe9b9145964c023ae066d.jpg',
      thumbImage: 'https://i.pinimg.com/736x/1c/7e/45/1c7e45a1ba3fe9b9145964c023ae066d.jpg',
      title: 'First Blood'
    }
  ];
}
