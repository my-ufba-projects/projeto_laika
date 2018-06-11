from django.db import models

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
    newsTitle = models.CharField(max_length=128)
    newsText = models.TextField()
    newsImage = models.ImageField(upload_to='news/images/')
    newsType = models.CharField(max_length=10, choices= TYPE_CHOICES, default=News)

    def save(self, *args,**kwargs):
        super(News,self).save(*args, **kwargs)
