# Generated by Django 2.0.5 on 2018-06-19 23:19

from django.db import migrations, models


class Migration(migrations.Migration):

    dependencies = [
        ('noticias', '0005_auto_20180619_2012'),
    ]

    operations = [
        migrations.AlterField(
            model_name='news',
            name='ntype',
            field=models.CharField(choices=[('News', 'News'), ('Ad', 'Ad'), ('AdoptionCenter', 'AdoptionCenter')], default='News', max_length=10),
        ),
    ]
