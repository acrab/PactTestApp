Pact.provider_states_for 'wiki_consumer' do

  provider_state "app wants data" do
    no_op # If there's nothing to do because the state name is more for documentation purposes,
          # you can use no_op to imply this.
  end

end

Pact.provider_states_for 'test_app' do

  provider_state "running" do
    no_op # If there's nothing to do because the state name is more for documentation purposes,
          # you can use no_op to imply this.
  end

end
