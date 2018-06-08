from .models import Animal
from .serializer import AnimalSerializer,UserSerializer
from django.contrib.auth.models import User
from rest_framework import permissions
from .permissions import IsOwnerOrReadOnly

from rest_framework import generics



