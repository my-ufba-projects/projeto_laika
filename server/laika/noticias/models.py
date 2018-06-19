from django.db import models
import datetime

# Create your models here.
class News(models.Model):
    News = 'News'
    Ad = 'Ad'
    AdoptionCenter = 'AdoptionCenter'
    TYPE_CHOICES = {
        (News, 'News'),
        (Ad, 'Ad'),
        (AdoptionCenter, 'AdoptionCenter')
    }
    title = models.CharField(max_length=128)
    text = models.TextField()
    image = models.ImageField(upload_to='news/images/')
    ntype = models.CharField(max_length=10, choices= TYPE_CHOICES, default=News)
    summary = models.TextField()
    date = models.DateField(default=datetime.date.today)

    def save(self, *args,**kwargs):
        super(News,self).save(*args, **kwargs)
