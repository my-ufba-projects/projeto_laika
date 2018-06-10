from django.db import models

# Create your models here.

class Animal(models.Model):
    sexo_escolhas = (('1', 'Fêmea'), ('2', 'Macho'), ('3', 'Não identificado'))
    pelagem_escolhas = (('1', 'Curta'), ('2', 'Longa'))

    nome = models.CharField(max_length=15)
    idade = models.DateField(blank=True,null=True)
    sexo = models.CharField(blank=True, max_length=1, choices=sexo_escolhas, default=None)
    pelagem = models.CharField(blank=True, max_length=1, choices=pelagem_escolhas, default=None)
    peso = models.IntegerField(blank=True, null=True)
    vermifugado = models.BooleanField()
    vacinado = models.BooleanField()
    descricao = models.CharField(blank=True, max_length=400)
    criador = models.ForeignKey('auth.user', related_name='animais', on_delete=models.CASCADE)

    def save(self, *args, **kwargs):
        super(Animal, self).save(*args, **kwargs)


class Cao(Animal):
    porte_escolhas = (('1', 'Pequeno'), ('2', 'Médio'), ('3', 'Grande'))
    porte = models.CharField(max_length=1, choices=porte_escolhas, default=None)

    def save(self, *args, **kwargs):
        super(Cao, self).save(*args, **kwargs)

class Gato(Animal):

    def save(self, *args, **kwargs):
        super(Gato, self).save(*args, **kwargs)