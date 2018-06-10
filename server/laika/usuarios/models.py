from django.db import models
from datetime import datetime

# Create your models here.
tipos_de_conta = [
    ('1', 'Doador'),
    ('2', 'Adotador'),
    ('9', 'Administrador')
]

class Perfil (models.Model):
    tipo_conta = models.CharField(max_length=1,choices=tipos_de_conta)
    nascimento = models.DateField()
    usuario = models.ForeignKey('auth.user', related_name='perfil',on_delete=models.CASCADE)
    email_primario = models.EmailField(null=True, blank=True)
    email_secundario = models.EmailField(null=True, blank=True)
    whatsapp = models.CharField(max_length=20, null=True,blank=True)
    facebook = models.CharField(max_length=100, null=True,blank=True)
    instagram = models.CharField(max_length=100, null=True,blank=True)
    telefone = models.CharField(max_length=20, null=True, blank=True)

    def save(self, *args,**kwargs):
        super(Perfil,self).save(*args,**kwargs)

