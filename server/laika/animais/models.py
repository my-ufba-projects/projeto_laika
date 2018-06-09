from django.db import models

# Create your models here.

class Animal(models.Model):
    sexo_escolhas = (('1', 'Fêmea'), ('2', 'Macho'), ('3', 'Não identificado'))

    nome = models.CharField(max_length=15)
    idade = models.IntegerField()
    sexo = models.CharField(max_length=1, choices=sexo_escolhas, default='Não identificado')
    peso = models.IntegerField()
    vermifugado = models.BooleanField()
    vacinado = models.BooleanField()
    descricao = models.CharField(max_length=400)
    criador = models.ForeignKey('auth.user', related_name='animais', on_delete=models.CASCADE)

    def save(self, *args,**kwargs):
        super(Animal,self).save(*args, **kwargs)

class Cao(Animal):
    porte_escolhas = (('1', 'Pequeno'), ('2', 'Médio'), ('3', 'Grande'), ('4', 'Extra Grande'))
    porte = models.CharField(max_length=1, choices=porte_escolhas, default='Não identificado')

    def save(self, *args,**kwargs):
        super(Cao,self).save(*args, **kwargs)

#class Gato(Animal):
    # Nothing here yet