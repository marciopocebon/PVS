# -*- mode: ruby -*-
# vi: set ft=ruby :

# All Vagrant configuration is done below. The "2" in Vagrant.configure
# configures the configuration version (we support older styles for
# backwards compatibility). Please don't change it unless you know what
# you're doing.
Vagrant.configure(2) do |config|
  # The most common configuration options are documented and commented below.
  # For a complete reference, please see the online documentation at
  # https://docs.vagrantup.com.

  # Every Vagrant development environment requires a box. You can search for
  # boxes at https://atlas.hashicorp.com/search.
  config.vm.box = "ubuntu/bionic64"

  # Disable automatic box update checking. If you disable this, then
  # boxes will only be checked for updates when the user runs
  # `vagrant box outdated`. This is not recommended.
  # config.vm.box_check_update = false

  # Create a forwarded port mapping which allows access to a specific port
  # within the machine from a port on the host machine. In the example below,
  # accessing "localhost:8080" will access port 80 on the guest machine.
  # config.vm.network "forwarded_port", guest: 80, host: 8080

  # Create a private network, which allows host-only access to the machine
  # using a specific IP.
  # config.vm.network "private_network", ip: "192.168.33.10"

  # Create a public network, which generally matched to bridged network.
  # Bridged networks make the machine appear as another physical device on
  # your network.
  # config.vm.network "public_network"

  # Share an additional folder to the guest VM. The first argument is
  # the path on the host to the actual folder. The second argument is
  # the path on the guest to mount the folder. And the optional third
  # argument is a set of non-required options.
  # config.vm.synced_folder "../data", "/vagrant_data"

  # Provider-specific configuration so you can fine-tune various
  # backing providers for Vagrant. These expose provider-specific options.
  # Example for VirtualBox:
  #
  config.vm.provider "virtualbox" do |vb|
    # Display the VirtualBox GUI when booting the machine
    vb.gui = true
  
    # Customize the amount of memory on the VM:
    vb.memory = "4096"
    vb.customize [ "modifyvm", :id, "--uartmode1", "disconnected" ]
  end
  #
  # View the documentation for the provider you are using for more
  # information on available options.

  # Define a Vagrant Push strategy for pushing to Atlas. Other push strategies
  # such as FTP and Heroku are also available. See the documentation at
  # https://docs.vagrantup.com/v2/push/atlas.html for more information.
  # config.push.define "atlas" do |push|
  #   push.app = "YOUR_ATLAS_USERNAME/YOUR_APPLICATION_NAME"
  # end

  # Enable provisioning with a shell script. Additional provisioners such as
  # Puppet, Chef, Ansible, Salt, and Docker are also available. Please see the
  # documentation for more information about their specific syntax and use.
  config.vm.provision "shell", inline: <<-SHELL
    sudo apt-get update
    sudo apt-get install -y virtualbox-guest-utils
    sudo apt-get install -y ubuntu-desktop
    sudo apt-get install -y unzip
    VBoxClient --display
    VBoxClient --draganddrop
    VBoxClient --seamless
    VBoxClient --clipboard
    sudo apt-get install -y emacs
    # Install PVS and Yices - downloaded from www.csl.sri.com/~owre/drop/pvs-snapshots
    # once I learn how (lftp comes close)
    pvsvers=851
    # curl "http://www.csl.sri.com/users/owre/drop/pvs-snapshots/pvs7.0-${pvsvers}.*.tgz"
    # vagrant /home/vagrant/pvs-7.0.${pvsvers} /home/vagrant/pvslib-master
    tar xf /vagrant/pvs7.0-${pvsvers}-*-ix86_64-Linux-allegro.tgz
    (cd /home/vagrant/pvs-7.0.${pvsvers}; ./install-sh)
    PATH=/home/vagrant/pvs-7.0.${pvsvers}:/home/vagrant/pvs-7.0.${pvsvers}/bin/ix86_64-Linux:$PATH
    echo "PATH=/home/vagrant/pvs-7.0.${pvsvers}:/home/vagrant/pvs-7.0.${pvsvers}/bin/ix86_64-Linux:$PATH" >> /home/vagrant/.bashrc
    # Inatall NASA's pvslib
    echo "(re)installing pvslib-master"
    unzip -o -q /vagrant/pvslib-master.zip
    echo "Changing ownership to vagrant user and group"
    sudo chown -R vagrant /home/vagrant/pvs-7.0.${pvsvers} /home/vagrant/pvslib-master
    sudo chgrp -R vagrant /home/vagrant/pvs-7.0.${pvsvers} /home/vagrant/pvslib-master
  SHELL
end
