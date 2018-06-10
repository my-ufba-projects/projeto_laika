from .models import Animal, Cao, Gato
from .serializer import AnimalSerializer, CaoSerializer, GatoSerializer
from .serializer import UserGatoSerializer, UserAnimalSerializer, UserCaoSerializer
from django.contrib.auth.models import User
from rest_framework import permissions
from .permissions import IsOwnerOrReadOnly

from rest_framework import generics


class AnimaisLista (generics.ListCreateAPIView):
    permission_classes = (permissions.IsAuthenticatedOrReadOnly,IsOwnerOrReadOnly,)
    queryset = Animal.objects.all()
    serializer_class = AnimalSerializer

    def perform_create(self, serializer):
        serializer.save(criador=self.request.user)


class AnimalDetalhe (generics.RetrieveUpdateDestroyAPIView):
    permission_classes = (permissions.IsAuthenticatedOrReadOnly,IsOwnerOrReadOnly,)
    queryset = Animal.objects.all()
    serializer_class = AnimalSerializer


class UserAnimaisLista (generics.ListAPIView):
    queryset = User.objects.all()
    serializer_class = UserAnimalSerializer


class UserAnimalDetalhe(generics.RetrieveAPIView):
    queryset = User.objects.all()
    serializer_class = UserAnimalSerializer


class CaesLista (generics.ListCreateAPIView):
    permission_classes = (permissions.IsAuthenticatedOrReadOnly,IsOwnerOrReadOnly,)
    queryset = Cao.objects.all()
    serializer_class = CaoSerializer

    def perform_create(self, serializer):
        serializer.save(criador=self.request.user)


class CaoDetalhe (generics.RetrieveUpdateDestroyAPIView):
    permission_classes = (permissions.IsAuthenticatedOrReadOnly,IsOwnerOrReadOnly,)
    queryset = Cao.objects.all()
    serializer_class = CaoSerializer



class UserCaesLista (generics.ListAPIView):
    queryset = User.objects.all()
    serializer_class = UserCaoSerializer


class UserCaoDetalhe(generics.RetrieveAPIView):
    queryset = User.objects.all()
    serializer_class = UserCaoSerializer


class GatosLista (generics.ListCreateAPIView):
    permission_classes = (permissions.IsAuthenticatedOrReadOnly,IsOwnerOrReadOnly,)
    queryset = Gato.objects.all()
    serializer_class = GatoSerializer

    def perform_create(self, serializer):
        serializer.save(criador=self.request.user)


class GatoDetalhe (generics.RetrieveUpdateDestroyAPIView):
    permission_classes = (permissions.IsAuthenticatedOrReadOnly,IsOwnerOrReadOnly,)
    queryset = Gato.objects.all()
    serializer_class = GatoSerializer


class UserGatosLista (generics.ListAPIView):
    queryset = User.objects.all()
    serializer_class = UserCaoSerializer


class UserGatoDetalhe(generics.RetrieveAPIView):
    queryset = User.objects.all()
    serializer_class = UserGatoSerializer


#test